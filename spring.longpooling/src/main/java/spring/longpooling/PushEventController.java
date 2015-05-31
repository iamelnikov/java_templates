package spring.longpooling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class PushEventController {

	private static final Long defResultTimeout = 50000L;
	
	@Autowired
	private PushEventService pushEventService;
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<Set<PushEvent>> getPushEventSet(){
		DeferredResult<Set<PushEvent>> result = new DeferredResult<Set<PushEvent>>(defResultTimeout);
		result.setResult(pushEventService.eventSet);
		return result;
	}
	
}
