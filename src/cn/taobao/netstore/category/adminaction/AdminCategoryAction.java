package cn.taobao.netstore.category.adminaction;

import java.util.List;

import cn.taobao.netstore.category.bean.Category;
import cn.taobao.netstore.category.service.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理
 * @author fangmin
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	
	//模型驱动
	private Category category = new Category();
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;//attempt to create saveOrUpdate event with null entity
	}
	
	//注入一级分类Service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//查询所有一级分类
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//保存一级分类
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//删除一级分类
	public String delete(){
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	//准备编辑一级分类
	public String edit(){
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	//修改一级分类
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}

}
