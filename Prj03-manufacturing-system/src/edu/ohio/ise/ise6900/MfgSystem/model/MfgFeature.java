package edu.ohio.ise.ise6900.MfgSystem.model;

public class MfgFeature extends MfgObject
{
	private Job job;

	public MfgFeature(String name){
		super(name);
	}
	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Feature (name="+ getName() + ", job=" + job + ")";
	}

}

