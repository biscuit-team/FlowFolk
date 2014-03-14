package assembler

import cn.sotou.tuningfork.util.PipeUtil
import cn.sotou.tuningfork.util.builtin.Get
import cn.sotou.tuningfork.util.provider.IBuiltinUtilAssembler
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.protocol.HttpContext

class GetAssembler implements IBuiltinUtilAssembler {

	@Override
	void assemble(PipeUtil pipeUtil) {

		Get getUtil = pipeUtil;

		//set cookies
		HttpContext context = getUtil.getContext();
		CookieStore cookieStore = new BasicCookieStore();

	}
}
