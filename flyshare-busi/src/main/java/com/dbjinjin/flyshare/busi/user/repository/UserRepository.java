package com.dbjinjin.flyshare.busi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbjinjin.flyshare.busi.user.model.User;

/**
 * <p>标题： UserRepository</p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月8日 上午10:40:07</p>
 * <p>类全名：com.dbjinjin.flyshare.busi.user.repository.UserRepository</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
}
