package com.aquarium.dev.filter

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DevFliter1 : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val req : HttpServletRequest = request as HttpServletRequest
        val res : HttpServletResponse = response as HttpServletResponse

        if(req.method.equals("POST")){
            println("POST 요청됨")
            val headerAuth : String = req.getHeader("Authorization")
            println("headerAuth : $headerAuth")
        }

        println("필터1")

        chain.doFilter(req, res)
    }

}