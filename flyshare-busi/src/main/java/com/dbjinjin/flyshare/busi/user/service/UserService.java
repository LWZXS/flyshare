package com.dbjinjin.flyshare.busi.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbjinjin.flyshare.busi.user.model.User;

/**
 * <p>标题： UserService</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月7日 下午4:33:03</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.user.service.UserService</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Repository
public interface UserService extends JpaRepository<User, Long>
{
}
