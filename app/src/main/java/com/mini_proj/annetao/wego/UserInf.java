package com.mini_proj.annetao.wego;

import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by bran on 2016/7/10.
 */
public class UserInf {
    private static UserInf userinf;
    private ArrayList<Exercise> exerciseAttendList;
    private ArrayList<Exercise> exerciseMyList;
    private ArrayList<UserNotice> noticeArrayList;

    private UserInf() {
        exerciseMyList = new ArrayList<>();
        exerciseAttendList = new ArrayList<>();
        noticeArrayList = new ArrayList<>();
        userinf = this;
    }

    public static UserInf getUserInf() {
        if (userinf == null)
            return new UserInf();
        return userinf;
    }

    public void doLogin(String user, Callback callback) {
        Map<String, String> map = new HashMap<>();
        //map.putAll(NetworkTools.paramsMap);
        map.put("id", user);
        //map.put("password", pass);
        NetworkTools.doRequest(NetworkTools.URL_USER + "/login", map, callback);
    }

    public void doRegister(String id, String name,String gender,String birthday, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("gender", gender);
        map.put("birthday", birthday);
        NetworkTools.doRequest(NetworkTools.URL_USER + "/register", map, callback);
    }

    public void doRegister(String name, String gender, String birthday, HashSet<Integer> tagIds, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("name", name);
        map.put("gender", gender);
        map.put("birthday", birthday);
        JSONObject jsonObject = new JSONObject();
        for (Integer id : tagIds)
            try {
                jsonObject.put(tagIds + "", Tag.value(id).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        map.put("tags", jsonObject.toString());
        NetworkTools.doRequest(NetworkTools.URL_USER + "/register", map, callback);
    }

    public void doChangeName(String name, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("name", name);
        NetworkTools.doRequest(NetworkTools.URL_USER + "/chguname", map, callback);
    }

    //notice
    public void queryMyNotice(Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        NetworkTools.doRequest(NetworkTools.URL_NOTICE + "/query_notice", map, callback);
    }

    public void readMyNotice(String id,Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("notice_id",id);
        NetworkTools.doRequest(NetworkTools.URL_NOTICE + "/read_notice", map, callback);
    }

    //Tag
    public void addUserTag(String tagid, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("tag_id", tagid);
        NetworkTools.doRequest(NetworkTools.URL_USER_TAG + "/add_user_tag", map, callback);
    }

    public void addUserNewTag(String tagname, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("name", tagname);
        NetworkTools.doRequest(NetworkTools.URL_USER_TAG + "/add_user_new_tag", map, callback);
    }

    //Tag
    public void deleteUserTag(String tagid, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("tag_id", tagid);
        NetworkTools.doRequest(NetworkTools.URL_USER_TAG + "/del_tag", map, callback);
    }

    public void queryUserTag(String tagname, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        NetworkTools.doRequest(NetworkTools.URL_USER_TAG + "/query_usr_tag", map, callback);
    }

    //attend
    public void addUserAttend(String tagname, String exercise_id, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("activity_id", exercise_id);
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/addusrActi", map, callback);
    }

    public void deleteUserAttend(String tagname, String exercise_id, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("activity_id", exercise_id);
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/delusrActi", map, callback);
    }

    public void queryIsAttend(String exercise_id, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("activity_id", exercise_id);
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/query_actiforusr", map, callback);
    }

    public void queryAllMyAttend(Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("type", "all");
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/query_allforusr", map, callback);
    }

    public void queryAllMyAttendWithTime(String time_lower_bound, String time_upper_bound, Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("time_lower_bound", time_lower_bound);
        map.put("time_upper_bound", time_upper_bound);
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/query_actibytime", map, callback);
    }

    public void queryMyAttendWithNoFinish(Callback callback) {
        Map<String, String> map = new HashMap<>();
        map.putAll(NetworkTools.paramsMap);
        map.put("type", "waiting");
        NetworkTools.doRequest(NetworkTools.URL_ATTENDENCY + "/query_actibeforeend", map, callback);
    }

    public ArrayList<Exercise> getExerciseMyList() {
        return exerciseMyList;
    }

    public void setExerciseMyList(ArrayList<Exercise> exerciseMyList) {
        this.exerciseMyList = exerciseMyList;
    }

    public void addExerciseMyList(Exercise exercise) {
        exerciseMyList.add(exercise);
    }

    public void addExerciseAttendList(Exercise exercise) {
        exerciseAttendList.add(exercise);
    }

    public ArrayList<Exercise> getExerciseAttendList() {
        return exerciseAttendList;
    }

    public void setExerciseAttendList(ArrayList<Exercise> exerciseAttendList) {
        this.exerciseAttendList = exerciseAttendList;
    }

    public ArrayList<UserNotice> getNoticeArrayList() {
        return noticeArrayList;
    }

    public void setNoticeArrayList(ArrayList<UserNotice> noticeArrayList) {
        this.noticeArrayList = noticeArrayList;
    }
    //attend
}