package cn.taobao.netstore.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.taobao.netstore.categorysecond.bean.Categorysecond;
import cn.taobao.netstore.categorysecond.service.CategorySecondService;
import cn.taobao.netstore.product.bean.Product;
import cn.taobao.netstore.product.service.ProductService;
import cn.taobao.netstore.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {

	// 模型驱动
	private Product product = new Product();

	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 接收page
	private Integer page;
	// 接收ProductService
	private ProductService productService;
	// 注入CategorySecondService
	private CategorySecondService categorySecondService;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 文件上传需要的三个属性
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	// 查询所有商品
	public String findAll() {	//private 报 java.lang.NoSuchMethodException
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 跳转到添加页面
	public String addPage() {
		// 查所有二级分类
		List<Categorysecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	// 保存商品
	public String save() throws IOException {
		product.setPdate(new Date());
		if (upload != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			File diskFile = new File(path + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	// 删除商品
	public String delete() {
		product = productService.findByPid(product.getPid());
		// 删除商品图片
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删数据库数据
		productService.delete(product);
		return "deleteSuccess";
	}

	// 准备修改商品
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<Categorysecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	// 修改商品
	public String update() throws IOException {
		product.setPdate(new Date());

		// 上传
		if (upload != null) {
			//删除旧图
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			
			//新的图片
			String path =  ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(path+"//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);

		}
		//修改，跳转
		productService.update(product);
		return "updateSuccess";
	}

}
