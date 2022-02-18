<?php

namespace App\Exceptions;

use Illuminate\Auth\AuthenticationException;
use Illuminate\Foundation\Exceptions\Handler as ExceptionHandler;
use Laravel\Sanctum\Exceptions\MissingAbilityException;
use Symfony\Component\HttpFoundation\File\Exception\AccessDeniedException;
use Symfony\Component\HttpKernel\Exception\AccessDeniedHttpException;
use Throwable;

class Handler extends ExceptionHandler
{
    /**
     * A list of the exception types that are not reported.
     *
     * @var array<int, class-string<Throwable>>
     */
    protected $dontReport = [
        //
    ];

    /**
     * A list of the inputs that are never flashed for validation exceptions.
     *
     * @var array<int, string>
     */
    protected $dontFlash = [
        'current_password',
        'password',
        'password_confirmation',
    ];

    /**
     * Register the exception handling callbacks for the application.
     *
     * @return void
     */
    public function register()
    {
        $this->renderable(function (AccessDeniedHttpException $e, $request) {
            $m = $e->getMessage();
            return response()->json(["message" => $m != null ? $m : "You don't have the rights"], 400);

        });
        $this->renderable(function (AuthenticationException $e, $request) {
            $m = $e->getMessage();
            return response()->json(["message" => $m != null ? $m : "You are not auth"], 400);
        });
        $this->renderable(function (MissingAbilityException $e, $request) {
            $m = $e->getMessage();
            return response()->json(["message" => $m != null ? $m : "You don't have the ability"], 400);
        });
    }
}
