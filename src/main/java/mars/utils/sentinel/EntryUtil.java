package mars.utils.sentinel;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

public class EntryUtil {

    public static void main(String [] args) {
//        initFlowRules();
        initDegradeRule();

        for (int i=0; i<100; i++) {
                Entry entry = null;
                try {
                    Thread.sleep(50);
                    entry = SphU.entry("resourceA");
                    /*您的业务逻辑 - 开始*/
                    test();
                    /*您的业务逻辑 - 结束*/
                } catch (DegradeException de) {
                    /* 熔断逻辑处理 - 开始 */
                    System.out.println("degrade");
                    /* 熔断逻辑处理 - 开始 */
                } catch (BlockException e1) {
                    /*流控逻辑处理 - 开始*/
                    System.out.println("block!");
                    /*流控逻辑处理 - 结束*/
                } catch (Exception e) {
                    Tracer.trace(e);
                    System.out.println("error");
                } finally {
                    if (entry != null) {
                        entry.exit();
                    }
                }
        }
    }

    private static void test() {
//        try {
//            Thread.sleep(600);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("test");
        throw new RuntimeException("throw runtime ");
    }

    private static void initFlowRules(){
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("resourceA");
        //流控-QPS模式（默认直接拒绝）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
    }

    private static void initDegradeRule() {
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("resourceA");
        //熔断 - 异常数量模式
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        degradeRule.setCount(3);
        degradeRule.setTimeWindow(10);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);

//        List<DegradeRule> rules = new ArrayList<DegradeRule>();
//        DegradeRule rule = new DegradeRule();
//        rule.setResource("resourceA");
//        // set threshold rt, 10 ms
//        rule.setCount(10);
//        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
//        rule.setTimeWindow(10);
//        rules.add(rule);
//        DegradeRuleManager.loadRules(rules);
    }

}
