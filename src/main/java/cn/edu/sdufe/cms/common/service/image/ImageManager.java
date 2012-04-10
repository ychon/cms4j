package cn.edu.sdufe.cms.common.service.image;

import cn.edu.sdufe.cms.common.dao.image.ImageDao;
import cn.edu.sdufe.cms.common.entity.image.Image;
import cn.edu.sdufe.cms.utilities.RandomString;
import cn.edu.sdufe.cms.utilities.thumb.ImageThumb;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * image 业务逻辑类
 * User: pengfei.dongpf@gmail.com
 * Date: 12-4-2
 * Time: 下午8:08
 */
@Component
@Transactional(readOnly = true)
public class ImageManager {

    private static Logger logger = LoggerFactory.getLogger(ImageManager.class);

    private ImageDao imageDao;

    /**
     * 获得分页的image
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<Image> getPagedImage(int offset, int limit) {
        return imageDao.getPagedImage(new RowBounds(offset, limit));
    }

    /**
     * 获得所有没有删除的image
     *
     * @return
     */
    public List<Image> getAllImageByDeleted() {
        return imageDao.findByDeleted(false);
    }

    /**
     * 获得所有image
     *
     * @return
     */
    public List<Image> getAllImage() {
        return (List<Image>) imageDao.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    /**
     * 获得首页显示的image
     *
     * @return
     */
    public List<Image> getImageByShowIndex() {
        return (List<Image>) imageDao.findByShowIndex(true);
    }

    /**
     * 获得编号为id的image
     *
     * @param id
     * @return
     */
    public Image getImage(Long id) {
        return imageDao.findOne(id);
    }

    /**
     * 添加image
     *
     * @param image
     * @return
     */
    @Transactional(readOnly = false)
    public Image save(MultipartFile file, HttpServletRequest request, Image image) {
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
            String fileName = this.upload(file, request);
            image.setImageUrl(fileName);
            //项目路径// TODO 迁移服务器需要修改
            String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\static\\uploads\\gallery\\";
            //图片来源路径

            ImageThumb imageThumb = new ImageThumb();
            try {
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "dashboard-thumb\\" + fileName, 50, 57);
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "photo-thumb\\" + fileName, 200, 122);
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "album-thumb\\" + fileName, 218, 194);

            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {
            image.setImageUrl("");
        }
        image.setDeleted(false);
        image.setLastModifiedDate(null);
        return imageDao.save(image);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @return
     */
    public String upload(MultipartFile file, HttpServletRequest request) {
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //上传路径
        String path = multipartRequest.getSession().getServletContext().getRealPath("static/uploads/gallery/gallery-big");
        //原文件名
        String imageName = file.getOriginalFilename();
        String ext = imageName.substring(imageName.lastIndexOf("."), imageName.length());
        //服务器上的文件名
        String fileName = new Date().getTime() + RandomString.get(6) + ext;
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 修改image
     *
     * @param file
     * @param request
     * @param image
     * @return
     */
    @Transactional(readOnly = false)
    public Image update(MultipartFile file, HttpServletRequest request, Image image) {
        this.deletePic("gallery-big\\" + image.getImageUrl());
        this.deletePic("dashboard-thumb\\" + image.getImageUrl());
        this.deletePic("photo-thumb\\" + image.getImageUrl());
        this.deletePic("album-thumb\\" + image.getImageUrl());

        //实现上传
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
            String fileName = this.upload(file, request);
            image.setImageUrl(fileName);
            //项目路径// TODO 迁移服务器需要修改
            String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\static\\uploads\\gallery\\";
            //图片来源路径

            ImageThumb imageThumb = new ImageThumb();
            try {
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "dashboard-thumb\\" + fileName, 50, 57);
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "photo-thumb\\" + fileName, 200, 122);
                imageThumb.saveImageAsJpg(path + "gallery-big\\" + fileName, path + "album-thumb\\" + fileName, 218, 194);

            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        image.setLastModifiedDate(null);
        return imageDao.save(image);
    }

    /**
     * 修改image
     *
     * @param image
     * @return
     */
    @Transactional(readOnly = false)
    public Image update(Image image) {
        return imageDao.save(image);
    }

    /**
     * 将image的删除标记置为true
     *
     * @param image
     */
    @Deprecated
    @Transactional(readOnly = false)
    public Image delete(Image image) {
        image.setDeleted(!image.isDeleted());
        return this.update(image);
    }

    /**
     * 将编号为id的image的删除标记置为true
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public Image delete(Long id) {
        Image image = this.getImage(id);
        image.setDeleted(!image.isDeleted());
        return this.update(image);
    }

    /**
     * 批量改变评论的删除标志
     *
     * @param ids
     */
    @Transactional(readOnly = false)
    public void batchDelete(String[] ids) {
        for (String id : ids) {
            Image image = this.getImage(Long.parseLong(id));
            image.setDeleted(true);
            this.update(image);
        }
    }

    /**
     * 任务删除图片
     */
    @Transactional(readOnly = false)
    public int delete() {
        List<Image> list = imageDao.findByDeleted(true);
        int count = list.size();
        while (list.size() > 0) {
            try {
                Image image = list.remove(0);
                imageDao.delete(image);
                deletePic(image.getImageUrl());
            } catch (Exception e) {
                logger.info("在批量删除文章时发生异常.");
            }
        }
        return count;
    }

    /**
     * 真正删除上传的图片
     *
     * @param fileName
     */
    public void deletePic(String fileName) {
        //上传路径
        String path = System.getProperty("user.dir");
        new File(path + "\\src\\main\\webapp\\static\\uploads\\gallery\\", fileName).delete();
    }

    @Autowired
    public void setImageDao(@Qualifier("imageDao") ImageDao imageDao) {
        this.imageDao = imageDao;
    }

}