package cn.taobao.netstore.category.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.taobao.netstore.categorysecond.bean.Categorysecond;
/**
 * 一级分类
 * @author fangmin
 *
 */
public class Category implements Serializable{
	private Integer cid;
	private String cname;
	
	//二级分类的集合
	private Set<Categorysecond> categoryseconds = new HashSet<Categorysecond>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<Categorysecond> getCategoryseconds() {
		return categoryseconds;
	}

	public void setCategoryseconds(Set<Categorysecond> categoryseconds) {
		this.categoryseconds = categoryseconds;
	}
	
	
}
