package com.aquarium.dev.filter

import java.io.IOException
import javax.servlet.*

class DevFliter2 : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("필터2")
        chain?.doFilter(request, response)
    }


}