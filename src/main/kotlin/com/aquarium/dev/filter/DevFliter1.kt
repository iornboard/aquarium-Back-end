package com.aquarium.dev.filter

import java.io.IOException
import java.io.PrintWriter
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DevFliter1 : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val req : HttpServletRequest = request as HttpServletRequest
        val res : HttpServletResponse = response as HttpServletResponse

        if(req.method.equals("POST")){

            val headerAuth : String? = req.getHeader("Authorization")
            println("headerAuth : $headerAuth")

            if(headerAuth.equals("hello")){
                chain.doFilter(req, res)
            }else{
                val outPrinterWriter : PrintWriter = res.writer
                println("인증안됨")
            }
        }
    }

}