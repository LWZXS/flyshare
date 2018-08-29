package com.dbjinjin.flyshare.ui.file.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbjinjin.flyshare.ui.file.model.FileUpload;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块：</p>
 * <p>版权： Copyright © 2018</p>
 * <p>创建日期：2018年8月29日 下午3:39:33</p>
 * <p>类全名：com.dbjinjin.flyshare.ui.file.repository.FileUpload</p>
 * 作者：wuxiaohai
 * 初审：
 * 复审：
 * @version 1.0
 */
@Repository
public interface FileUploadRepository extends CrudRepository<FileUpload, Long> 
{
}
