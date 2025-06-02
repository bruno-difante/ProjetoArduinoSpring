package com.brunodifante.projetoarduino.controller;

import com.brunodifante.projetoarduino.model.Led;
import com.brunodifante.projetoarduino.model.LogAcao;
import com.brunodifante.projetoarduino.repository.LogAcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ControleArduinoController {

    private final Led led = new Led("COM5", 9600); // Ajuste a COM se necessário

    @Autowired
    private LogAcaoRepository logRepo;

    @GetMapping
    public String paginaInicial(Model model) {
        model.addAttribute("ligado", led.isLigado());
        model.addAttribute("logs", logRepo.findAll());
        return "index";
    }

    @PostMapping("/toggle")
    @ResponseBody
    public String alternarLuz() {
        led.alternar();
        String acao = led.isLigado() ? "LIGAR" : "DESLIGAR";
        logRepo.save(new LogAcao(acao));
        return acao;
    }

    @Autowired
    private LogAcaoRepository logAcaoRepository;

    @PostMapping("/limpar-logs")
    @ResponseBody
    public String limparLogs() {
        logAcaoRepository.deleteAll();
        return "Logs apagados!";
    }

}