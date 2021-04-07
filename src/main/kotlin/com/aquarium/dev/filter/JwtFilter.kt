package com.aquarium.dev.filter

import java.io.IOException
import java.io.PrintWriter
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("필터1")
        chain?.doFilter(request, response)
    }
}