package cn.edu.sdufe.cms.common.dao.account;

import cn.edu.sdufe.cms.common.dao.GenericDao;
import cn.edu.sdufe.cms.common.entity.account.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户Dao
 * User: baitao.jibt (dreambt@gmail.com)
 * Date: 12-3-20
 * Time: 下午20:34
 */
@Component
public interface UserMapper extends GenericDao<User, Long> {

    List<User> getAll();

    /**
     * 获取邮箱为email的用户，仅用于登录
     *
     * @param email
     * @return
     */
    User getByEmail(String email);

    int deleteByTask();

}