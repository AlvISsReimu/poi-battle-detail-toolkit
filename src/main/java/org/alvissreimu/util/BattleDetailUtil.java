/**  
* @Title: BattleDetailUtil.java
* @Package org.alvissreimu.util
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 4:25:43 PM
* @version V1.0  
*/
package org.alvissreimu.util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.alviss.data.BattleDetail;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
* @ClassName: BattleDetailUtil
* @Description: Methods to handle battle details
* @author Minghao Li
* @date Jul 24, 2018 4:25:43 PM
*/
public class BattleDetailUtil {
	
	private static BattleDetailUtil instance = null;
	private static final Logger logger = Logger.getLogger(BattleDetailUtil.class);
	
	private BattleDetailUtil() {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static BattleDetailUtil getInstance() {
		if (instance == null)
			instance = new BattleDetailUtil();
		return instance;
	}
	
	/** 
	* @Title: importBattleDetailsFromCSV 
	* @Description: import battle details CSV as a list
	* @param path CSV's path
	* @return List<BattleDetail>
	*/
	public List<BattleDetail> importBattleDetailsFromCSV(String path) {
		List<BattleDetail> details = new LinkedList<>();
		try {
			Reader in = new InputStreamReader(new FileInputStream(path), "UTF-8");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Timestamp1", "Timestamp2", "Map", "Node", "Description", "Grade").parse(in);
			for (CSVRecord record : records) {
				details.add(new BattleDetail(
						Long.parseLong(record.get("Timestamp1")), 
						record.get("Map"), 
						record.get("Node"), 
						record.get("Description"), 
						record.get("Grade")));
			}
		} catch (IOException e) {
			logger.error("Fail to import battle details.", e);
		}
		return details;
	}
	
	/** 
	* @Title: writeCSV 
	* @Description: print CSV file using given battle details
	* @param details battle details to be printed
	* @param path CSV's path
	* @return void
	*/
	public void writeCSV(List<BattleDetail> details, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (BattleDetail detail: details) {
				writer.write(detail.toString());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			logger.error("Fail to write CSV.", e);
		}
	}
	
	/** 
	* @Title: combineDetails 
	* @Description: combine two groups of battle details and sort in descending order
	* @param d1
	* @param d2
	* @return List<BattleDetail>
	*/
	public List<BattleDetail> combineDetails(List<BattleDetail> d1, List<BattleDetail> d2) {
		List<BattleDetail> details = new LinkedList<>();
		for (BattleDetail d: d1)
			details.add(d);
		for (BattleDetail d: d2)
			details.add(d);
		Collections.sort(details, (a, b) -> (int)(b.getTimestamp() - a.getTimestamp()));
		return details;
	}
	
	public void importBattleDetails(String path) {
		
	}
	
}
