package ro.oho.rest.model;


public class Hobby implements Cloneable{
	private int idHobby;
	private String hobbyName;
	private String description;
	private String videoURL;
	private String imageURL;
	private int approved;
	
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getIdHobby() {
		return idHobby;
	}
	public void setIdHobby(int idHobby) {
		this.idHobby = idHobby;
	}
	public String getHobbyName() {
		return hobbyName;
	}
	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
	return hobbyName+" "+description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((hobbyName == null) ? 0 : hobbyName.hashCode());
		result = prime * result + idHobby;
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + ((videoURL == null) ? 0 : videoURL.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hobby other = (Hobby) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hobbyName == null) {
			if (other.hobbyName != null)
				return false;
		} else if (!hobbyName.equals(other.hobbyName))
			return false;
		if (idHobby != other.idHobby)
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (videoURL == null) {
			if (other.videoURL != null)
				return false;
		} else if (!videoURL.equals(other.videoURL))
			return false;
		
		if(this.idHobby!=other.getIdHobby())
			return false;
		
		if(this.hobbyName.equals(other.getHobbyName())==false)
			return false;
		
		if(this.description.equals(other.getDescription())==false)
			return false;
		
		if(this.getImageURL().equals(other.getImageURL())==false)
			return false;
		
		if(this.getVideoURL().equals(other.getVideoURL())==false)
			return false;
		
		return true;
	}
	
	public Hobby clone(){
		Hobby hobby=new Hobby();
		hobby.setApproved(this.approved);
		hobby.setDescription(this.description);
		hobby.setHobbyName(this.hobbyName);
		hobby.setIdHobby(this.idHobby);
		hobby.setImageURL(this.imageURL);
		hobby.setVideoURL(this.videoURL);
		return hobby;
	}
}
