package org.ethh.dal.service.base;

import lombok.Data;

/**
 * BasePageReq
 *
 * @author GZM
 * @since 2024/12/18 下午4:27
 */
@Data
public class BasePageReq {
	
	/**
	 * 每页显示条数，默认 10
	 */
	long size = 10;
	/**
	 * 当前页
	 */
	long current = 1;
	
}
