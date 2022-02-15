<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;
use Symfony\Component\Config\Definition\Exception\Exception;
use Tymon\JWTAuth\Exceptions\JWTException;
use Tymon\JWTAuth\Exceptions\TokenExpiredException;
use Tymon\JWTAuth\Exceptions\TokenInvalidException;
use Tymon\JWTAuth\Facades\JWTAuth;
use Tymon\JWTAuth\Http\Middleware\BaseMiddleware;

class JwtVerify extends BaseMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure(\Illuminate\Http\Request): (\Illuminate\Http\Response|\Illuminate\Http\RedirectResponse)  $next
     * @return \Illuminate\Http\Response|\Illuminate\Http\RedirectResponse
     */
    public function handle(Request $request, Closure $next)
    {
        try {
            $user = $this->auth->parseToken()->authenticate();
        }catch (JWTException $e){
            if($e instanceof TokenInvalidException){
                return response()->json(["status"=>"Token is invalid"],400);
            }else if($e instanceof TokenExpiredException){
                return response()->json(["status"=>"Token is expired"],400);

            }else{
                return response()->json(["status"=>"Token not found"],400);

            }
        }
        return $next($request);
    }
}
