<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use App\Models\User;
use Tymon\JWTAuth\Facades\JWTAuth;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller
{
    public function index(Request $request)
    {
        $token = JWTAuth::getToken();

        $user = $this->getAuthenticatedUser($token);
        dd(JWTAuth::toUser($token));
//        if (!$user->can('viewAny', $user)) {
//            return response()->json(["message" => "You cannot view all the users"], 400);
//        }
        return UserResource::collection(User::all());
    }

    /**
     * @throws \Illuminate\Validation\ValidationException
     */
    public function auth(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'login' => 'required|string',
            'password' => 'required|string'
        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $token = auth()->attempt($validator->validated());
        if (!$token) {
            return response()->json(['error' => 'Unauthorized'], 400);
        }

        return $this->createNewToken($token);
    }

    /**
     * @throws \Illuminate\Validation\ValidationException
     */
    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'login' => 'required|string|max:255',
            'password' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:users',
            'name' => 'required|string',
            'firstname' => 'required|string',
            'age' => 'required|integer|min:1'
        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $validated = $validator->validated();
        $validated["password"] = bcrypt($validated["password"]);
        $validated["role"] = "user";
        $user = User::create($validated);
//        $token = $user->createToken('Laravel8PassportAuth')->accessToken;
        return response()->json([
            'data' => $user,
//            'token'=>$token

        ], 201);

    }

    protected function createNewToken($token): \Illuminate\Http\JsonResponse
    {
        return response()->json([
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60,
            'user' => auth()->user()
        ]);
    }

    public function userInfo()
    {

        $user = auth()->user();

        return response()->json(['user' => $user], 200);

    }
}
