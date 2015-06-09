/**
 * @author Pinellia
 *
 */
package dw.spring3.rest.ResourceModel;


import java.io.Serializable;

public class RUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Integer sex;
	
	public RUser() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
}