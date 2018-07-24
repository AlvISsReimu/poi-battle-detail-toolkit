/**  
* @Title: GzipUtil.java
* @Package org.alvissreimu.util
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 11:56:26 AM
* @version V1.0  
*/
package org.alvissreimu.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
* @ClassName: GzipUtil
* @Description: Gzip compression and decompression methods
* @author Minghao Li
* @date Jul 24, 2018 11:56:26 AM
*/
public class GzipUtil {
	
	private static GzipUtil instance = null;
	
	private GzipUtil() {}
	
	public static GzipUtil getInstance() {
		if (instance == null)
			instance = new GzipUtil();
		return instance;
	}
	
	/** 
	* @Title: decompressGzipFile 
	* @Description: Decompress gzip file
	* @param gzipFile gz file to be decompressed
	* @param newFile path of the new file
	* @return void
	*/
	public void decompressGzipFile(String gzipFile, String newFile) {
		try {
			FileInputStream fis = new FileInputStream(gzipFile);
			GZIPInputStream gis = new GZIPInputStream(fis);
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gis.read(buffer)) != -1){
				fos.write(buffer, 0, len);
			}
			fos.close();
			gis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* @Title: compressGzipFile 
	* @Description: Compress gz file
	* @param file file to be compressed
	* @param gzipFile path of the gz file
	* @return void
	*/
	public void compressGzipFile(String file, String gzipFile) {
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(gzipFile);
			GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				gzipOS.write(buffer, 0, len);
			}
			gzipOS.close();
			fos.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GzipUtil util = GzipUtil.getInstance();
		util.decompressGzipFile("1532079931728.json.gz", "ungzipped/111.json");
		util.compressGzipFile("ungzipped/111.json", "ungzipped/newFile.json.gz");
	}
	
}
