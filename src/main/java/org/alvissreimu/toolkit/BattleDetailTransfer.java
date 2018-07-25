/**  
* @Title: BattleDetailTransfer.java
* @Package org.alvissreimu.toolkit
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 4:29:06 PM
* @version V1.0  
*/
package org.alvissreimu.toolkit;

import java.io.File;

import org.alvissreimu.util.BattleDetailUtil;
import org.alvissreimu.util.GzipUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
* @ClassName: BattleDetailTransfer
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 4:29:06 PM
*/
public class BattleDetailTransfer {
	
	private static final Logger logger = Logger.getLogger(BattleDetailTransfer.class);
	private static final String CSV_NAME = "index11";
	private static final String GZIP_EXTENSION = ".gz";
	private static final String CSV_EXTENSION = ".csv";
	private static final String UNGZIPPED_DIRECTORY = "ungzipped";
	
	private static BattleDetailTransfer instance = null;
	private static GzipUtil gzipUtil = GzipUtil.getInstance();
	private static BattleDetailUtil bdUtil = BattleDetailUtil.getInstance();
	
	private BattleDetailTransfer() {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static BattleDetailTransfer getInstance() {
		if (instance == null)
			instance = new BattleDetailTransfer();
		return instance;
	}
	
	public void transfer(String sourcePath, String destPath) {
		decompressGzipBattleDetail(sourcePath, CSV_NAME + "_source" + CSV_EXTENSION);
		decompressGzipBattleDetail(destPath, CSV_NAME + "_dest" + CSV_EXTENSION);
	}
	
	private void decompressGzipBattleDetail(String path, String newName) {
		String sourceGzipFileName = CSV_NAME + CSV_EXTENSION + GZIP_EXTENSION;
		File sourceGzip = new File(path + File.separator + sourceGzipFileName);
		if (!sourceGzip.exists()) {
			logger.error("Cannot find " + CSV_NAME + CSV_EXTENSION + GZIP_EXTENSION);
			return;
		}
		gzipUtil.decompressGzipFile(sourceGzip.getAbsolutePath(), UNGZIPPED_DIRECTORY + File.separator + newName);
	}
	
	public static void main(String[] args) {
		BattleDetailTransfer s = BattleDetailTransfer.getInstance();
		s.transfer("battle-detail-source", "battle-detail-destination");
	}
	
}
