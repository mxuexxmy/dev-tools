package work.longxing.devtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.longxing.devtools.utils.SnowFlakeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ID 生成器控制成
 * @author mxuexxmy
 */
@RequestMapping
@Controller
public class IdController {

    @GetMapping
    public String home(ModelMap map) {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        map.put("id", snowFlakeUtil.nextId());
        return "index";
    }

    @GetMapping("/number")
    public String idOfNumber(ModelMap map) {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        map.put("id", snowFlakeUtil.nextId());
        return "index";
    }

    @GetMapping("/batch-generator")
    public String batchGenerator(@RequestParam(value = "num") Integer num, ModelMap map) {
        List<Long> res = new ArrayList<>();
        if (num == null) {
            SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
            res.add(snowFlakeUtil.nextId());
            map.put("id", res);
        } else {
            for (int i = 0; i < num; i++) {
                SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
                res.add(snowFlakeUtil.nextId());
                map.put("id", res);
            }
        }

        return "index";
    }

    @GetMapping("/id-generate")
    public String idGenerator(Model model) {
        StringBuilder sb = new StringBuilder();
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(11, 11);
        for (int i = 0; i < 10; i++) {
            Long id = snowFlakeUtil.nextId();
            sb.append(id);
            if (i < 10 -1) {
               sb.append("\n");
            }
        }
        model.addAttribute("textareaValue", sb.toString());
        return "id-generate.html";
    }

}
