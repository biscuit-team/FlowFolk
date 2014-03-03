package assembler

import cn.sotou.flowfolk.util.PipeUtil
import cn.sotou.flowfolk.util.provider.IBuiltinUtilAssembler

class GetAssembler implements IBuiltinUtilAssembler {

    @Override
    void assemble(PipeUtil pipeUtil) {

        System.out.println("assembler.GetAssembler#assemble()");

    }
}
