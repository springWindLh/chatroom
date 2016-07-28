package lh.chatroom.controller;

import lh.chatroom.controller.support.AjaxResponse;
import lh.chatroom.controller.support.BaseController;
import lh.chatroom.domain.User;
import lh.chatroom.form.UserForm;
import lh.chatroom.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by lh on 2016/7/7.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse register(@RequestBody @Valid UserForm form, BindingResult result) {
        final String errorMsg = "注册失败";
        if (result.hasErrors()) {
            return getErrorInfo(result,errorMsg);
        }
        User user = form.asUser();
        try {
            userService.create(user);
            if (authenticate(user.getName(), user.getPassword())) {
                return AjaxResponse.ok().msg("注册成功").jumpUrl("/home");
            } else {
                return AjaxResponse.ok().msg("注册失败");
            }
        } catch (Exception e) {
            return AjaxResponse.fail().msg(errorMsg).detail(e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse login(String name, String password, Boolean rememberMe) {
        if (authenticate(name, password)) {
            return AjaxResponse.ok().msg("登录成功").jumpUrl("/home");
        } else {
            return AjaxResponse.fail().msg("用户名或密码错误");
        }
    }

    private boolean authenticate(String name, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            //调用loadUserByUserName
            SecurityContextHolder.getContext().setAuthentication(authentication);
            getRequest().getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

}
