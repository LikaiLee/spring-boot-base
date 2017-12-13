package ${basePackage}.controller;

import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ${author} on ${date}.
 */
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("/add")
    public String add(${modelNameUpperCamel} ${modelNameLowerCamel}, HttpServletResponse response) {
        int flag = ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, HttpServletResponse response) {
        int flag = ${modelNameLowerCamel}Service.deleteById(id);
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/update")
    public String update(${modelNameUpperCamel} ${modelNameLowerCamel}, HttpServletResponse response) {
        int flag = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        if (flag == 1) {
            return ResultGenerator.ajaxReturn(response, "", "success", flag);
        } else {
            return ResultGenerator.ajaxReturn(response, "", "fail", 0);
        }
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam Integer id, HttpServletResponse response) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.ajaxReturn(response, ${modelNameLowerCamel}, "", 0);
    }

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, HttpServletResponse response) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return ResultGenerator.ajaxReturn(response, pageInfo, "", 0);
    }
}
