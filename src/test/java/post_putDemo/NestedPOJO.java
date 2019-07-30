package post_putDemo;

import java.util.ArrayList;

//-----------------------------------com.sample1.Category.java-----------------------------------

import java.util.List;



class Category {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
// -----------------------------------com.sample1.Mysam1.java-----------------------------------

public class NestedPOJO {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls = null;
	private List<Tag> tags = null;
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	
	public List<Tag> getTags() {
        return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
    	 int sizee=tags.size();
//         for(int i=0;i<sizee;i++)
//	     {
//	        System.out.println("element id at "+i+" is " +tags.get(i).getId());
//	        System.out.println("element name at "+i+" is "+tags.get(i).getName());
//	    }
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	


}
// -----------------------------------com.sample1.Tag.java-----------------------------------

class Tag {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
