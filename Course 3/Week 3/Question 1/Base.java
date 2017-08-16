import java.util.*;
class Base implements Comparable<Base>,Comparator<Base> {
	private String vertex;
	private int frequency;
	private Base left;
	private Base right;
	private String code = "";
	private static int count=0;
	public Base() {}
	public Base(String vertex,int frequency) {
		this.vertex = vertex;
		this.frequency = frequency;
	}
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getVertex() {
		return this.vertex;
	}
	public int getFrequency() {
		return this.frequency;
	}
	public void setLeft(Base left) {
		this.left = left;
	}
	public Base getLeft() {
		return this.left;
	}
	public void setRight(Base right) {
		this.right = right;
	}
	public Base getRight() {
		return this.right;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
	}
	public int compareTo(Base base) {
		return this.frequency - base.frequency;
	}
	public int compare(Base b1,Base b2) {
		if (b1.getCode().length() == b2.getCode().length()) {
			return b1.getVertex().compareTo(b2.getVertex());
		}
		else {
			return b1.getCode().length() - b2.getCode().length();
		}
	}
	public String toString() {
		count++;
		// if(count == 1) {
		// 	return vertex+" ";
		// }
		return vertex+":"+code;
	}
}