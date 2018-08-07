package java_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivilegedActionException;

import org.omg.PortableInterceptor.IORInterceptor;

//public class Test {
//
//	public static void main(String[] args) {
//		File file=new File("C:"+File.separator+"Users"+File.separator+"蒋瑜"+File.separator+"Desktop"+File.separator+"test.txt");
//		if(!file.getParentFile().exists()) {
//			file.getParentFile().mkdirs();
//		}
//		try {
//			OutputStream outputStream=new FileOutputStream(file);
//			String str="hello world\r\n";
//			outputStream.write(str.getBytes());
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(file.exists()&&file.isFile()) {
//			try {
//				InputStream inputStream=new FileInputStream(file);
//				byte [] temp=new byte[1024];
//				int len =inputStream.read(temp);
//				String result=new String(temp, 0, len);
//				System.out.println(result);
//				inputStream.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	public static void printAllFile(File file) {
//		if(file.exists()&&file.isDirectory()) {
//			File [] result=file.listFiles();
//			for (File file2 : result) {
//				printAllFile(file2);
//			}
//		}else if(file.exists()&&file.isFile()) {
//			System.out.println(file);
//		}else {
//			return ;
//		}
//	}
//
//}

public class Test{
	public static void main(String[] args) throws Exception {
		String sourceFilePath="C:\\Users\\蒋瑜\\Desktop\\青海湖.jpg";
		String destFilePath="C:\\\\Users\\\\蒋瑜\\\\Desktop\\\\青海湖1.jpg";
		System.out.println(fileCopy(sourceFilePath,destFilePath)?"文件拷贝成功":"文件拷贝失败");
	}
	public static boolean fileCopy(String sourceFilePath,String destFilePath) throws Exception {
		File sourceFile=new File(sourceFilePath);
		File destFile=new File(destFilePath);
		InputStream inputStream=new FileInputStream(sourceFile);
		OutputStream outputStream=new FileOutputStream(destFile);
		return fileCopyHander(inputStream, outputStream);
	}
	private static boolean fileCopyHander(InputStream in,OutputStream out) throws Exception {
		int len=0;
		byte [] result=new byte[1024];
		long start=System.currentTimeMillis();
		while((len=in.read(result))!=-1) {
			out.write(result);
		}
		long end=System.currentTimeMillis();
		System.out.println("文件拷贝用时："+(end-start)+"");
		in.close();
		out.close();
		return true;
	}
}












