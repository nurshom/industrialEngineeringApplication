package edu.ohio.ise.ise6900.MfgSystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.ohio.ise.ise6900.MfgSystem.model.exceptions.*;

public class Job extends MfgObject
{
	public int batchSize;
	public Map<String, MfgFeature> features;
	public ArrayList<Activity> activities;

	public Job(String name, int batchSize){
		super(name);
		this.batchSize = batchSize;
		this.features = new HashMap<String, MfgFeature>();
		this.activities = new ArrayList<Activity>();
	}

	public void addFeature(MfgFeature f) throws AlreadyMemberException {
		try{
			this.findFeature(f.getName());
			throw new AlreadyMemberException("Feature '" + f.getName() 
								+ "' is already in job '" + this.getName() + "'.");
		}catch(UnknownObjectException uoe){
			features.put(f.getName(), f);
		}		
	}
	
	public MfgFeature findFeature(String featureName) throws UnknownObjectException {
		MfgFeature feature = features.get(featureName);
		if(feature == null){
			throw new UnknownObjectException("Feature with name '" + featureName 
								+ "' does not exist in job '" + this.getName() + "'.");
		}
		return feature;
	}
	
	public MfgFeature deleteFeature(String featureName){
		return this.features.remove(featureName);
	}
	

	public void listFeatures(){
		this.printout();
		MfgObject.io.println("=>Features:");
		for(MfgFeature f : this.features.values()){
			f.printout();
		}
	}
	

	public void addActivity(Activity a) throws AlreadyMemberException {
		activities.add(a);
	}
	
	public Activity findActivity(MfgFeature feature) throws InvalidStateException {
		Activity activity = new Activity("", null, this, feature, new Date(0), new Date(1000));
		for(Activity act : activities){
			if(act.equals(activity)){
				return activities.get(activities.indexOf(act));
			}
		}
		return null;
	}
	
	public boolean deleteActivity(Activity activity) {
		for(Activity act : activities){
			if(act.equals(activity)){
				return activities.remove(act);
			}
		}
		return false;
	}
	
	public void listActivities(){
		this.printout();
		MfgObject.io.println("=> Activities:");
		for(Activity a : this.activities){
			a.printout();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job " + this.getName() + ", batchSize=" + batchSize;
	}


}

