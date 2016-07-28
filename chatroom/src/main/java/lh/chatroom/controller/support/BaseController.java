package lh.chatroom.controller.support;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import lh.chatroom.domain.CurrentUser;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lh on 2016/4/8.
 */
public class BaseController {
    public static final String RESOURCE_NOT_FOUND = "resourceNotFound";

    private HttpServletRequest request;

    protected void setPageTitle(Model model, String title) {
        model.addAttribute("pageTitle", title);
    }

    protected void enableGoBack(Model model) {
        model.addAttribute("pageGoBack", true);
    }

    protected Object toJson(Object object) {
        return JSON.toJSON(object);
    }

    protected List<Long> getIdsList(List<String> ids) {
        return ids.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected CurrentUser getCurrentUser() {
        return (CurrentUser) getRequest().getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    }

    protected AjaxResponse getErrorInfo(BindingResult result, String errorMsg) {
        if (Strings.isNullOrEmpty(errorMsg)) {
            return AjaxResponse.fail().detail(result.getAllErrors().get(0).getDefaultMessage());
        } else {
            return AjaxResponse.fail().msg(errorMsg).detail(result.getAllErrors().get(0).getDefaultMessage());
        }
    }
}
