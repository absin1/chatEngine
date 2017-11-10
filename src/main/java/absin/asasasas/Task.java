package absin.asasasas;

public class Task {
	int id;
	String header;
	String title;
	String description;
	String itemType;
	int itemId;
	int duration;
	String imageURL;
	String status;
	String date;
	String messageForCompletedTasks;
	String messageForIncompleteTasks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessageForCompletedTasks() {
		return messageForCompletedTasks;
	}

	public void setMessageForCompletedTasks(String messageForCompletedTasks) {
		this.messageForCompletedTasks = messageForCompletedTasks;
	}

	public String getMessageForIncompleteTasks() {
		return messageForIncompleteTasks;
	}

	public void setMessageForIncompleteTasks(String messageForIncompleteTasks) {
		this.messageForIncompleteTasks = messageForIncompleteTasks;
	}

	public Task(int id, String header, String title, String description, String itemType, int itemId, int duration,
			String imageURL, String status, String date, String messageForCompletedTasks,
			String messageForIncompleteTasks) {
		super();
		this.id = id;
		this.header = header;
		this.title = title;
		this.description = description;
		this.itemType = itemType;
		this.itemId = itemId;
		this.duration = duration;
		this.imageURL = imageURL;
		this.status = status;
		this.date = date;
		this.messageForCompletedTasks = messageForCompletedTasks;
		this.messageForIncompleteTasks = messageForIncompleteTasks;
	}

}
