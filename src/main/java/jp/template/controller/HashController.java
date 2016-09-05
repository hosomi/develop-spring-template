package jp.template.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hash 機能を提供します。
 * <p>現状はテストから呼び出されるのみ。</p>
 * 
 * @author hosomi.
 */
@Controller
@RequestMapping(value = "/hash")
public class HashController {

	/**
	 * エンコード処理。
	 * 
	 * @param password エンコードする対象のパラメータ。
	 * @return エンコード後の値（直接レスポンスします）。
	 */
	@RequestMapping(value = "/encode", produces="text/plain;charset=UTF-8") // 文字コードの指定しなければデフォルトは ISO-8859-1
	@ResponseBody
	public String encode(@RequestParam String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
