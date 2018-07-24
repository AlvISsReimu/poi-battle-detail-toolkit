/**  
* @Title: BattleDetailAnalyzer.java
* @Package org.alvissreimu.toolkit
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 11:53:26 AM
* @version V1.0  
*/
package org.alvissreimu.toolkit;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
* @ClassName: BattleDetailAnalyzer
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 11:53:26 AM
*/
public class BattleDetailAnalyzer {
	
	private static final String battleDetailPath = "~/Library/Application Support/poi/battle-detail";
	
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
			e.printStackTrace();
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
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		BattleDetailAnalyzer analyzer = new BattleDetailAnalyzer();
		List<BattleDetail> details1 = analyzer.importBattleDetailsFromCSV("ungzipped/1.csv");
		List<BattleDetail> details2 = analyzer.importBattleDetailsFromCSV("ungzipped/2.csv");
		List<BattleDetail> details = analyzer.combineDetails(details1, details2);
		for (BattleDetail d: details)
			System.out.println(d);
	}
	
}
