package corp.asbp.platform.cmqs.service;

import corp.asbp.platform.cmqs.model.Email;
import corp.asbp.platform.cmqs.model.EmailConfig;
/**
 * @author Narendra Padala
 *
 */
public interface NotificationService {
	
	public void notifyEmail(Email email);
	
	public void notifyEmail(Email email,EmailConfig config);

}
