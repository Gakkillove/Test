package base;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import base.B_time;

@WebServlet("/I_Upload")
public class I_Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public I_Upload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		C_Filter.Encoding(request, response);
		try{
			//使用Apache文件上传组件处理文件上传步骤：
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8"); 
			//3、判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				return;
			}
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
		
			//fileitem中封装的是普通输入项的数据
			//String name = list.get(0).getFieldName();
			//解决普通输入项的数据的中文乱码问题
			String folder = list.get(0).getString("UTF-8");

			//得到上传文件的保存目录
			String savePath = C_Cmd.Add_src[1]+folder;
			File file = new File(savePath);
	 		//判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("目录不存在或不合法");
				C_Filter.put("n_folder", response);
				//创建目录
				//file.mkdir();
			}else {
				//fileitem中封装的是上传文件
				//得到上传的文件名称，
				String filename = list.get(1).getName();
				System.out.println(filename);
				if(filename==null || filename.trim().equals("")){
					System.out.println("文件名为空");
				}
				//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
				//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
				filename = filename.substring(filename.lastIndexOf("\\")+1);
				long Size=list.get(1).getSize()/1024;//kb
				//获取item中的上传文件的输入流
				InputStream in = list.get(1).getInputStream();
				//创建一个文件输出流lon
				FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
				//创建一个缓冲区
				byte buffer[] = new byte[1024];
				//判断输入流中的数据是否已经读完的标识
				int len = 0;
				//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while((len=in.read(buffer))>0){
					//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					out.write(buffer, 0, len);
				}
				//关闭输入流
				in.close();
				//关闭输出流
				out.close();
				//删除处理文件上传时生成的临时文件
				list.get(1).delete();
				
				//文件重命名(要用符合操作系统格式的绝对路径)
				file=new File(savePath + "\\" + filename);
				String lastname="diancanyun"+B_time.Timerm()+filename;
				String newfilename=savePath + "\\" + lastname;
				file.renameTo(new File(newfilename));
				//图片压缩
				PicZip picZip=new PicZip();
				picZip.Piczp(600, newfilename);
				
				//**********OSS上传*******************************************// Endpoint以杭州为例，其它Region请按实际情况填写。
				String endpoint = "oss-cn-chengdu.aliyuncs.com";
				// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
				String accessKeyId = "LTAI4FdJJmXo4NhmrerXRbV5";
				String accessKeySecret = "tWDAbgREBVLZRpRE28E9CayQV3ndpK";

				// 创建OSSClient实例。
				OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

				// 创建PutObjectRequest对象。
				PutObjectRequest putObjectRequest = new PutObjectRequest("diancanyun", folder+"/"+lastname, new File(newfilename));

				// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
				// ObjectMetadata metadata = new ObjectMetadata();
				// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
				// metadata.setObjectAcl(CannedAccessControlList.Private);
				// putObjectRequest.setMetadata(metadata);

				// 上传文件。
				ossClient.putObject(putObjectRequest);

				// 关闭OSSClient。
				ossClient.shutdown(); 
				//*****************************************************
				
				C_Filter.put(lastname, response);
			}
		}catch (Exception e) {
			C_Filter.put("n_do", response);
			e.printStackTrace();
		}
	}

}
