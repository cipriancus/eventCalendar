package ro.oho.rest.login.facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import ro.oho.rest.model.User;

public class FBGraph {
	private String accessToken;

	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBGraph() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=id,first_name,last_name,birthday,email,picture&" + accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public User getGraphData(String fbGraph) {
		User user=new User();
		try {
			JSONObject json = new JSONObject(fbGraph);
			user.setIdUser(Long.parseLong(json.getString("id")));
			user.setNume(json.getString("first_name"));
			user.setPrenume(json.getString("last_name"));
			user.setDataNasterii("08/03/1995");
			user.setEmail(json.getString("email"));
			user.setAuthToken(accessToken.substring(13));
			user.setAuthSite("Facebook");
			user.setPicture(json.getJSONObject("picture").getJSONObject("data").getString("url"));
			user.setGrad(2);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return user;
	}
}
