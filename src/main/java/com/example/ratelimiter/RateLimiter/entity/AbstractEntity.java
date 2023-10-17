package com.example.ratelimiter.RateLimiter.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity<PK> implements Serializable {

	private static final long serialVersionUID = 8453654076725018243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Nullable PK id;

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Version
	@Column
	private int version;

	
	


	public AbstractEntity() {

		setCreated(new Date());

	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@PreUpdate
	public void preUpdate() {
		lastModified = new Date();
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

}
