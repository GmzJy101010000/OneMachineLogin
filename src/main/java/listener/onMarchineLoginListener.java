package listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import entity.User;

@WebListener
public class onMarchineLoginListener implements HttpSessionAttributeListener {

Map<String, HttpSession>  allUserSessionMap =new ConcurrentHashMap<String, HttpSession>();
	
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
        
        String name= event.getName();
    	
    	if("user".equals(name)){
            
    		User user =(User)event.getValue();
    		allUserSessionMap.remove(user.getName());
    		System.out.println("session remove");
    	}
    	System.out.println(allUserSessionMap.size());
    }

	
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	 String name= event.getName();
         System.out.println("add"+name);
     	if("user".equals(name)){
            User newUser =(User)event.getValue();  
            
            if(allUserSessionMap.get(newUser.getName()) != null){
         	   
         	   HttpSession oldSession = allUserSessionMap.get(newUser.getName());
         	   User oldUser =(User) oldSession.getAttribute("user");
         	   System.out.println(oldSession);
         	   oldSession.removeAttribute("user");
         	   oldSession.setAttribute("msg", "add:您的账号在别的地方登录，您已被迫从"+oldUser.getIp()+"下线！！");
         	  
            }
            allUserSessionMap.put(newUser.getName(), event.getSession());
            System.out.println(event.getSession());
        	 System.out.println("add session attribute for"+newUser.getName());
     		System.out.println(allUserSessionMap.size());
     	}
    
    	
    }

    /* trigger if call setAttribute more than one time for a same key
     *  eg:  
    session.setAttribute("test", "11");
    session.setAttribute("test", "22");*/
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
        
       System.out.println("replace");
    	 String name= event.getName();
        System.out.println(name);
        System.out.println(event.getValue()+" "+event.getSession().getAttribute(name));
      	if("user".equals(name)){
      User  oldUser =(User)event.getValue();
      allUserSessionMap.remove(oldUser.getName());
      
      User newUser =(User) event.getSession().getAttribute("user");
      
      if(allUserSessionMap.get(newUser.getName()) !=null){
    	  
    	   HttpSession oldSession = allUserSessionMap.get(newUser.getName());
     	   User oldUser1 =(User) oldSession.getAttribute("user");
     	   System.out.println(oldSession);
     	   oldSession.removeAttribute("user");
     	   oldSession.setAttribute("msg", "replace:您的账号在别的地方登录，您已被迫从"+oldUser1.getIp()+"下线！！");
    	  
      }
      allUserSessionMap.put(newUser.getName(), event.getSession());
      System.out.println(event.getSession());
  	  System.out.println("add session attribute for"+newUser.getName());
	  System.out.println(allUserSessionMap.size());
    }
    }
}
