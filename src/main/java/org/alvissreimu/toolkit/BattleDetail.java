/**  
* @Title: BattleDetail.java
* @Package org.alvissreimu.toolkit
* @Description: TODO
* @author Minghao Li
* @date Jul 24, 2018 2:47:32 PM
* @version V1.0  
*/
package org.alvissreimu.toolkit;

/**
* @ClassName: BattleDetail
* @Description: Battle detail data structure
* @author Minghao Li
* @date Jul 24, 2018 2:47:32 PM
*/
public class BattleDetail {
	
	private Long timestamp;
	private String map;
	private String node;
	private String description;
	private String grade;
	
	public BattleDetail(Long timestamp, String map, String node, String description, String grade) {
		this.timestamp = timestamp;
		this.map = map;
		this.node = node;
		this.description = description;
		this.grade = grade;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	/*
	* <p>Title: toString</p>
	* <p>Description: </p>
	* @return
	* @see java.lang.Object#toString()
	*/ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(timestamp);
		sb.append(",");
		sb.append(timestamp);
		sb.append(",");
		sb.append(map);
		sb.append(",");
		sb.append(node);
		sb.append(",");
		sb.append(description);
		sb.append(",");
		sb.append(grade);
		return sb.toString();
	}
	
}
