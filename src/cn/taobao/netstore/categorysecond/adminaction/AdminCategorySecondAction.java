package cn.taobao.netstore.categorysecond.adminaction;

import java.util.List;

import cn.taobao.netstore.category.bean.Category;
import cn.taobao.netstore.category.service.CategoryService;
import cn.taobao.netstore.categorysecond.bean.Categorysecond;
import cn.taobao.netstore.categorysecond.service.CategorySecondService;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<Categorysecond> {
	//模型驱动
	private Categorysecond categorysecond = new Categorysecond();
	public Categorysecond getModel() {
		// TODO Auto-generated method stub
		return categorysecond;
	}
	
	//接收page参数
	private Integer page;
	//接收二级Service
	private CategorySecondService categorySecondService;
	//接收一级分类Service
	private CategoryService categoryService;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//带分页查询所有二级分类
	public String findAll(){
		PageBean<Categorysecond> pageBean = categorySecondService.findByPage(page);
		//存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加页面
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		//存到值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	
	//添加二级分类
	public String save(){
		categorySecondService.save(categorysecond);
		return "saveSuccess";
	}
	
	//删除二级分类
	public String delete(){
		categorySecondService.delete(categorysecond);
		return "deleteSuccess";
	}
	
	//准备更新二级分类
	public String edit(){
		categorysecond = categorySecondService.findByCCid(categorysecond.getCcid());
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//更新二级分类、
	public String update(){
		categorySecondService.update(categorysecond);
		return "updateSuccess";
	}
}
