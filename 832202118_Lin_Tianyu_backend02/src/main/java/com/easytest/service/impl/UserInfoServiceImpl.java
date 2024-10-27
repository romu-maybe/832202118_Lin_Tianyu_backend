package com.easytest.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.easytest.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.easytest.entity.enums.PageSize;
import com.easytest.entity.query.UserInfoQuery;
import com.easytest.entity.po.UserInfo;
import com.easytest.entity.vo.PaginationResultVO;
import com.easytest.entity.query.SimplePage;
import com.easytest.mappers.UserInfoMapper;
import com.easytest.service.UserInfoService;
import com.easytest.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户信息 业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserInfo> findListByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserInfo> list = this.findListByParam(param);
		PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserInfo bean) {
		return this.userInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserInfo bean, UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserInfo getUserInfoByUserId(Integer userId) {
		return this.userInfoMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoByUserId(UserInfo bean, Integer userId) {
		return this.userInfoMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoByUserId(Integer userId) {
		return this.userInfoMapper.deleteByUserId(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUserInfo(UserInfo userInfo) {
		if(userInfo.getUserId()==null){
			userInfo.setCreateTime(new Date());
			this.userInfoMapper.insert(userInfo);
		}else{
			userInfo.setPassword(null);
			userInfo.setCreateTime(null);
			this.userInfoMapper.updateByUserId(userInfo,userInfo.getUserId());
		}
		UserInfoQuery userInfoQuery = new UserInfoQuery();
		userInfoQuery.setPhone(userInfo.getPhone());
		Integer count = this.userInfoMapper.selectCount(userInfoQuery);
		if(count>1){
			throw new BusinessException("手机号重复");
		}
	}
}