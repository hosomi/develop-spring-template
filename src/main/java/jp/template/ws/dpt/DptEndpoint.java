package jp.template.ws.dpt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import jp.template.domain.Dpt;
import jp.template.mapper.DptMapper;

@Endpoint
public class DptEndpoint {

	private static final String NAMESPACE_URI = "http://github.com/hosomi/develop-spring-template";

	@Autowired
	private DptMapper dptMapper;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDptRequest")
	@ResponsePayload
	public GetDptResponse getUsers(@RequestPayload GetDptRequest dptRequest) {
		final GetDptResponse getDptResponse = new GetDptResponse();
		final String cddpt = dptRequest.getCddpt();

		Dpt dpt = dptMapper.selectByCddpt(cddpt);

		final List<Dpt> list = new ArrayList<Dpt>();
		list.add(dpt);
		getDptResponse.setList(list);
		return getDptResponse;
	}
}
