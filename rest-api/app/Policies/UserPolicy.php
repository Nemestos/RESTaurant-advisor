<?php

namespace App\Policies;

use App\Models\User;
use Illuminate\Auth\Access\HandlesAuthorization;
use Illuminate\Auth\Access\Response;

class UserPolicy
{
    use HandlesAuthorization;

    /**
     * Create a new policy instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }
    public function store(User $user){
        return true;
    }
    public function update(User $user,User $model){
        return $user->role == "admin" || $user->id == $model->id;

    }
    public function delete(User $user, User $model){
        return $user->role == "admin" || $user->id == $model->id;


    }
    public function viewAny(User $user){
        return $user->role == "admin";

    }
}
