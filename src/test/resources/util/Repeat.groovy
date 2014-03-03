package util

import cn.sotou.flowfolk.util.BasePipeUtil
import org.apache.commons.lang.StringUtils

class Repeat extends BasePipeUtil {

    @Override
    InputStream[] process(InputStream inputStream, String... args) throws Exception {
        int repeatTime = 1;
        if (args.length > 0) {
            repeatTime = Integer.parseInt(args[0]);
        }

        return toInputStream(StringUtils.repeat(readerFromStream(inputStream), repeatTime));
    }
}

