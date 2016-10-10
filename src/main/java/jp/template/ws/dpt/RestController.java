package jp.template.ws.dpt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.template.domain.Dpt;
import jp.template.mapper.DptMapper;

@Controller
@RequestMapping(value = "/rest")
public class RestController {

	@Autowired
	private DptMapper dptMapper;
	
	@ResponseBody
	@RequestMapping(value = "/dpt/json/{cddpt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Dpt> dptJson(@PathVariable String cddpt) {

		Dpt dpt = dptMapper.selectByCddpt(cddpt);
		List<Dpt> list = new ArrayList<Dpt>();
		list.add(dpt);

		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dpt/xml/{cddpt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)//produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
	public List<Dpt> dptXml(@PathVariable String cddpt) {

		Dpt dpt = dptMapper.selectByCddpt(cddpt);
		List<Dpt> list = new ArrayList<Dpt>();
		list.add(dpt);
		
		return list;
	}

}
