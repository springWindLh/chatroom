package lh.chatroom.controller;

import lh.chatroom.controller.support.AjaxResponse;
import lh.chatroom.controller.support.BaseController;
import lh.chatroom.domain.Room;
import lh.chatroom.form.RoomForm;
import lh.chatroom.query.RoomQuery;
import lh.chatroom.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lh on 2016/7/19.
 */
@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {
    @Autowired
    private IRoomService roomService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(RoomQuery query, Model model) {
        PagedListHolder<Room> listHolder = roomService.findByQueryItems(query.asQueryItems(), query.getPage(), query.getPageSize(), "createdTime", Sort.Direction.DESC);
        model.addAttribute("rooms", listHolder);
        return "/room/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse create(@RequestBody @Valid RoomForm form, BindingResult result) {
        final String errorMsg = "创建房间失败";
        if (result.hasErrors()) {
            return getErrorInfo(result, errorMsg);
        }
        Room room = form.asRoom();
        try {
            roomService.create(room);
            return AjaxResponse.ok().msg("创建房间成功").jumpUrl("/room/list");
        } catch (Exception e) {
            return AjaxResponse.fail().msg(errorMsg).detail(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse update(@RequestBody @Valid RoomForm form, BindingResult result) {
        final String errorMsg = "房间信息更新失败";
        if (result.hasErrors()) {
            return getErrorInfo(result, errorMsg);
        }
        Room room = form.asRoom();
        try {
            roomService.create(room);
            return AjaxResponse.ok().msg("房间信息更新成功").jumpUrl("/room/list");
        } catch (Exception e) {
            return AjaxResponse.fail().msg(errorMsg).detail(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse delete(@PathVariable String id) {
        try {
            roomService.delete(id);
            return AjaxResponse.ok().msg("房间删除成功");
        } catch (Exception e) {
            return AjaxResponse.fail().msg("房间删除失败").detail(e.getMessage());
        }
    }
}
