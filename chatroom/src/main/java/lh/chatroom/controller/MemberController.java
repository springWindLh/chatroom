package lh.chatroom.controller;

import lh.chatroom.controller.support.AjaxResponse;
import lh.chatroom.controller.support.BaseController;
import lh.chatroom.domain.Member;
import lh.chatroom.form.MemberForm;
import lh.chatroom.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by lh on 2016/7/26.
 */
@Controller
public class MemberController extends BaseController {
    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse create(@RequestBody @Valid MemberForm form, BindingResult result) {
        final String errorMsg = "加入失败";
        if (result.hasErrors()) {
            return getErrorInfo(result, errorMsg);
        }
        Member member = form.asMember();
        try {
            memberService.create(member);
            return AjaxResponse.ok().msg("加入成功").jumpUrl("/room/list");
        } catch (Exception e) {
            return AjaxResponse.fail().msg(errorMsg).detail(e.getMessage());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse update(String nickName, @PathVariable String id) {
        final String errorMsg = "昵称更新失败";
        Optional<Member> memberOptional = memberService.getById(id);
        if (!memberOptional.isPresent()) {
            return AjaxResponse.fail().msg("昵称更新失败").detail("成员不存在");
        }
        try {
            memberService.updateNickName(memberOptional.get());
            return AjaxResponse.ok().msg("昵称更新成功").jumpUrl("/room/list");
        } catch (Exception e) {
            return AjaxResponse.fail().msg(errorMsg).detail(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse delete(@PathVariable String id) {
        Optional<Member> memberOptional = memberService.getById(id);
        if (!memberOptional.isPresent()) {
            return AjaxResponse.fail().msg("昵称更新失败").detail("成员不存在");
        }
        try {
            memberService.delete(memberOptional.get());
            return AjaxResponse.ok().msg("退出成功");
        } catch (Exception e) {
            return AjaxResponse.fail().msg("退出失败").detail(e.getMessage());
        }
    }
}
