package com.example.Coin2Ether;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class Coin2EtherController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/simulateOnce")
    public String handleFormSimulateOnce(@RequestParam Map<String, String> params, Model model) {
        // 获取卡包版本
        String packSelect = params.get("packSelect");
        int version = Integer.parseInt(packSelect);

        String coinTotal = params.get("coinTotal");
        int coin = Integer.parseInt(coinTotal);

        String toMustRainbow = params.get("toMustRainbow");
        int mustRainbow = Integer.parseInt(toMustRainbow);

        String rainbow0Of3 = params.get("rainbow0Of3");
        int r03 = Integer.parseInt(rainbow0Of3);

        String rainbow1Of3 = params.get("rainbow1Of3");
        int r13 = Integer.parseInt(rainbow1Of3);

        String rainbow2Of3 = params.get("rainbow2Of3");
        int r23 = Integer.parseInt(rainbow2Of3);

        String rainbow3Of3 = params.get("rainbow3Of3");
        int r33 = Integer.parseInt(rainbow3Of3);

        String gold0Of3 = params.get("gold0Of3");
        int g03 = Integer.parseInt(gold0Of3);

        String gold1Of3 = params.get("gold1Of3");
        int g13 = Integer.parseInt(gold1Of3);

        String gold2Of3 = params.get("gold2Of3");
        int g23 = Integer.parseInt(gold2Of3);

        String gold3Of3 = params.get("gold3Of3");
        int g33 = Integer.parseInt(gold3Of3);

        Coin2Ether.toEther ob = new Coin2Ether.toEther(version, coin, mustRainbow, r03, r13, r23, r33,
                                                                            g03, g13, g23, g33);
        int result = ob.card2Ether();

        model.addAttribute("result", result);
        return "result";
    }

    @PostMapping("/simulateMany")
    public String handleFormSimulateMany(@RequestParam Map<String, String> params, Model model) {

        String packSelect = params.get("packSelect");
        int version = Integer.parseInt(packSelect);

        String coinTotal = params.get("coinTotal");
        int coin = Integer.parseInt(coinTotal);

        String toMustRainbow = params.get("toMustRainbow");
        int mustRainbow = Integer.parseInt(toMustRainbow);

        String rainbow0Of3 = params.get("rainbow0Of3");
        int r03 = Integer.parseInt(rainbow0Of3);

        String rainbow1Of3 = params.get("rainbow1Of3");
        int r13 = Integer.parseInt(rainbow1Of3);

        String rainbow2Of3 = params.get("rainbow2Of3");
        int r23 = Integer.parseInt(rainbow2Of3);

        String rainbow3Of3 = params.get("rainbow3Of3");
        int r33 = Integer.parseInt(rainbow3Of3);

        String gold0Of3 = params.get("gold0Of3");
        int g03 = Integer.parseInt(gold0Of3);

        String gold1Of3 = params.get("gold1Of3");
        int g13 = Integer.parseInt(gold1Of3);

        String gold2Of3 = params.get("gold2Of3");
        int g23 = Integer.parseInt(gold2Of3);

        String gold3Of3 = params.get("gold3Of3");
        int g33 = Integer.parseInt(gold3Of3);

        double result = 0;
        for(int i = 0; i < 10000; i++){
            Coin2Ether.toEther t = new Coin2Ether.toEther(version, coin, mustRainbow, r03, r13, r23, r33,
                    g03, g13, g23, g33);
            result += t.card2Ether();
        }

        model.addAttribute("result", result / 10000);
        return "result";
    }
}

