package com.house.controller;

import java.io.File;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.house.entity.Course;
import com.house.entity.CourseSelected;
import com.house.entity.User;


import com.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.house.service.IHouserService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddHouseController {

	@Autowired
	private IUserService CSservice;
	private String uname;

	private String dirPath = "D:/upload/";
	// 简介图片地址
	private String simplePath = "";
	// 详细图片地址
	private StringBuilder detailsPath = new StringBuilder();;
	
	@Autowired
	private IHouserService service;
	@Autowired
	IUserService iUserService;

	@RequestMapping("/MultipleUpload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> file, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String,Object>();
		if (!file.isEmpty() && file.size() > 0) {
			for (MultipartFile f : file) {
				try {
					// 文件名
					String filename = UUID.randomUUID()
							+ f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
					// 存储虚拟路径
					String localPath = req.getServletContext().getContextPath() + "/file/" + filename;
					detailsPath.append(localPath+"~");

					File filePath = new File(dirPath);
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					//上传
					f.transferTo(new File(dirPath + filename));

				} catch (Exception e) {
					map.put("code", 1);
					map.put("msg", "上传失败");
					e.printStackTrace();
				}
			}
			map.put("code", 0);
			map.put("msg", "上传成功");
		}
		return map;
	}

	@RequestMapping("/singleUpload")
	@ResponseBody
	public Map<String, Object> singleUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String filename = UUID.randomUUID() + suffixName;
			File filePath = new File(dirPath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			//创建虚拟路径存储
			simplePath = req.getServletContext().getContextPath() + "/file/" + filename;
			map.put("image", simplePath);
			file.transferTo(new File(dirPath + filename));
			map.put("code", 0);
			map.put("msg", "上传成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "上传失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/addHouse")
	public String addHouse() {
		return "addhouse.jsp";
	}
	@RequestMapping("/recommend")
	public ModelAndView recommend(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		this.uname=user.getUname();
		CourseSelected[] CS=CSservice.getInfo();

		for(int i=0;i<CS.length;i++){
			System.out.println(CS[i].getCourse_code()+CS[i].getUname()+CS[i].getMark());
		}
		System.out.println();

		Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> currentUser = new HashMap<String, Map<String, Integer>>();



		Map<String, Integer> pref1 = new HashMap<String, Integer>();
		Map<String,Integer>[] persons = new Map[CS.length];
		persons[0] = new HashMap();
		persons[0].put(CS[0].getCourse_code(),CS[0].getMark());
		userPerfMap.put(CS[0].getUname(),persons[0]);
		int coun=1;
		for(int i=1;i<CS.length;i++){
			persons[i] = new HashMap();
			for(int j=coun;j<CS.length;j++){
				persons[i].put(CS[j].getCourse_code(),CS[j].getMark());
				System.out.println(CS[j].getUname()+"===="+CS[j-1].getUname()+"   "+i+"=="+j);
				if(((CS[j].getUname()).equals(CS[j-1].getUname()))){

					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

				}else{
					Map<String, Integer> a =userPerfMap.put(CS[j].getUname(),persons[i]);
					coun=j;
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+a+userPerfMap.size()+userPerfMap.entrySet());

				}

			}


		}




			Map<String, Double> simUserSimMap = new HashMap<String, Double>();
		System.out.println("皮尔逊相关系数:"+uname);

		for(Entry<String,Map<String,Integer>> userperEn:userPerfMap.entrySet()){
			String username=userperEn.getKey();
			System.out.println(username+userperEn.getValue().size());
			if(!"uname".equals(username)){
				double sim = getUserSimilar(userperEn.getValue(), userperEn.getValue());
				System.out.println(uname+"与" + username + "之间的相关系数:" + sim);
				simUserSimMap.put(username, sim);
			}
		}
		Map<String, Map<String, Integer>> simUserObjMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> pobjMap1 = new HashMap<String, Integer>();

		for(Entry<String,Map<String,Integer>> p:userPerfMap.entrySet()){

			simUserObjMap.put(p.getKey(), p.getValue());

		}

		System.out.println("推荐结果:" + getRecommend(simUserObjMap, simUserSimMap));
		ModelAndView mv=new ModelAndView();
		Course c=new Course();
		c.setCourse_code(getRecommend(simUserObjMap, simUserSimMap));
		Course courseName=CSservice.selectCourseName(c);
		mv.addObject("personnal",courseName.getCourse_name());
		//mv.addObject("personnal",getRecommend(simUserObjMap, simUserSimMap));
		mv.setViewName("recommend.jsp");
		return mv;
	}
	//Claculate Pearson Correlation Coefficient
	public static double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {
		int n = 0;// 数量n
		int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
		int sx = 0;// Σx=x1+x2+....xn
		int sy = 0;// Σy=y1+y2+...yn
		int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
		int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
		for (Entry<String, Integer> pme : pm1.entrySet()) {
			String key = pme.getKey();
			Integer x = pme.getValue();
			Integer y = pm2.get(key);
			if (x != null && y != null) {
				n++;
				sxy += x * y;
				sx += x;
				sy += y;
				sx2 += Math.pow(x, 2);
				sy2 += Math.pow(y, 2);
			}
		}
		// p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
		double sd = sxy - sx * sy / n;
		double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
		return Math.abs(sm == 0 ? 1 : sd / sm);
	}

	//获取推荐结果
	public static String getRecommend(Map<String, Map<String, Integer>> simUserObjMap,
									  Map<String, Double> simUserSimMap) {
		Map<String, Double> objScoreMap = new HashMap<String, Double>();
		for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {
			String user = simUserEn.getKey();
			double sim = simUserSimMap.get(user);
			for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {
				double objScore = sim * simObjEn.getValue();//加权（相似度*评分）
				String objName = simObjEn.getKey();
				if (objScoreMap.get(objName) == null) {
					objScoreMap.put(objName, objScore);
				} else {
					double totalScore = objScoreMap.get(objName);
					objScoreMap.put(objName, totalScore + objScore);//将所有用户的加权评分作为最后的推荐结果数据
				}
			}
		}
		List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(objScoreMap.entrySet());
		Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {//排序
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				Double a = o1.getValue() - o2.getValue();
				if (a == 0) {
					return 0;
				} else if (a > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		for (Entry<String, Double> entry : enList) {
			System.out.println(entry.getKey()+"的加权推荐值:"+entry.getValue());
		}
		return enList.get(enList.size() - 1).getKey();//返回推荐结果
	}

	@RequestMapping("/addHouseRecord")
	@ResponseBody
	public String validationInfo(String id,String school,int stu_number,String stu_name,String telephone,String email,int sex) {
		User oldUser = new User();
		oldUser.setUid(Integer.parseInt(id));
		//oldUser.setPassword(oldPwd);
		//User checkUser = iUserService.checkOldPwd(oldUser);
		//if(checkUser!=null) {
			User newUser = new User();
			newUser.setUid(Integer.parseInt(id));
			newUser.setSchool(school);
		    newUser.setStu_name(stu_name);
		    newUser.setStu_number(stu_number);
		    newUser.setTelephone(telephone);
		    newUser.setEmail(email);
		    newUser.setSex(sex);
			int n = iUserService.validationInfo(newUser);
			if(n>0) {
				return "OK";
			}
		//}
		return "FAIL";
	}
}
