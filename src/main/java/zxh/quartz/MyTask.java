package zxh.quartz;

public class MyTask {
	private String name;
	
	public void run() {
		System.out.println("Run task: " + name + ".");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
